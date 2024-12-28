import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;

public class JarvisAssistant {
    public static void main(String[] args) {
        try {
            // Set up speech recognition
            Configuration configuration = new Configuration();
            configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
            configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
            configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

            LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
            recognizer.startRecognition();

            // Set up text-to-speech
            Voice voice = VoiceManager.getInstance().getVoice("kevin16");
            if (voice != null) {
                voice.allocate();
            }

            // Set up video capture
            OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
            grabber.start();
            CanvasFrame canvas = new CanvasFrame("Video Capture");

            System.out.println("Listening...");

            while (canvas.isVisible()) {
                // Capture video frame
                Mat frame = grabber.grab();
                if (frame != null) {
                    canvas.showImage(new OpenCVFrameConverter.ToMat().convert(frame));
                }

                // Speech recognition
                SpeechResult result = recognizer.getResult();
                if (result != null) {
                    String spokenText = result.getHypothesis();
                    System.out.println("You said: " + spokenText);

                    // Command handling
                    if (spokenText.equalsIgnoreCase("hello")) {
                        voice.speak("Hello! How can I assist you?");
                    } else if (spokenText.equalsIgnoreCase("time")) {
                        String currentTime = java.time.LocalTime.now().toString();
                        voice.speak("Current time is " + currentTime);
                    } else if (spokenText.equalsIgnoreCase("exit")) {
                        voice.speak("Goodbye!");
                        break;
                    } else {
                        voice.speak("Sorry, I didn't understand that.");
                    }
                }
            }

            // Clean up
            recognizer.stopRecognition();
            grabber.stop();
            canvas.dispose();
            voice.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
