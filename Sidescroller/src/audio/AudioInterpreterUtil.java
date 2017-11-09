package audio;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class AudioInterpreterUtil {
	 
	public String interpret(String fileName) throws Exception {
	
		// Arraylist of string will be returned
		//ArrayList<String> resultsArray = new ArrayList<String>();
		String returnString = "";
		
	    // Instantiates a client
	    SpeechClient speech = SpeechClient.create();

	    // Reads the audio file into memory
	    Path path = Paths.get(fileName);
	    byte[] data = Files.readAllBytes(path);
	    ByteString audioBytes = ByteString.copyFrom(data);

	    // Builds the sync recognize request
	    RecognitionConfig config = RecognitionConfig.newBuilder()
	        .setEncoding(AudioEncoding.LINEAR16)
	        .setSampleRateHertz(16000)
	        .setLanguageCode("en-US")
	        .build();
	    RecognitionAudio audio = RecognitionAudio.newBuilder()
	        .setContent(audioBytes)
	        .build();

	    // Performs speech recognition on the audio file
	    RecognizeResponse response = speech.recognize(config, audio);
	    List<SpeechRecognitionResult> results = response.getResultsList();

	    for (SpeechRecognitionResult result: results) {
	    	// There can be several alternative transcripts for a given chunk of speech. Just use the
	    // first (most likely) one here.
	    		SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
	    		System.out.printf("Transcription: %s%n", alternative.getTranscript());
	    		returnString = alternative.getTranscript();
	    }
	    
	    speech.close();
	    
	    return returnString;
	}
}