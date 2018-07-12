package com.gt.speectotextgarantiunifiedcomm;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class SpeectoTextGarantiUnifiedcommApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeectoTextGarantiUnifiedcommApplication.class, args);

		go();
	}

	private static void go() {

		try (SpeechClient speechClient = SpeechClient.create()) {

			Path path = Paths.get("\\\\pgarappstp01\\Share\\karisik.flac");
			byte[] data = Files.readAllBytes(path);
			ByteString audioBytes = ByteString.copyFrom(data);

			RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.FLAC;
			int sampleRateHertz = 44100;
			String languageCode = "tr-TR";
			RecognitionConfig config = RecognitionConfig.newBuilder()
					.setEncoding(encoding)
					.setSampleRateHertz(sampleRateHertz)
					.setLanguageCode(languageCode)
					.build();

			RecognitionAudio audio = RecognitionAudio.newBuilder()
					.setContent(audioBytes)
					.build();
			RecognizeResponse response = speechClient.recognize(config, audio);

			List<SpeechRecognitionResult> results = response.getResultsList();

			System.out.println("RESULTS :");
			for (SpeechRecognitionResult result : results) {
				// There can be several alternative transcripts for a given chunk of speech. Just use the
				// first (most likely) one here.
				SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
				System.out.printf("Transcription: %s%n", alternative.getTranscript());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("END :");

	}
}
