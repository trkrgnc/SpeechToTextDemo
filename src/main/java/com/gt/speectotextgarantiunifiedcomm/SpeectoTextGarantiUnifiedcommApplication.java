package com.gt.speectotextgarantiunifiedcomm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;

import java.io.IOException;

@SpringBootApplication
public class SpeectoTextGarantiUnifiedcommApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeectoTextGarantiUnifiedcommApplication.class, args);

		go();
	}

	private static void go() {

		try (SpeechClient speechClient = SpeechClient.create()) {
			RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.FLAC;
			int sampleRateHertz = 44100;
			String languageCode = "en-US";
			RecognitionConfig config = RecognitionConfig.newBuilder()
					.setEncoding(encoding)
					.setSampleRateHertz(sampleRateHertz)
					.setLanguageCode(languageCode)
					.build();
			String uri = "file:\\\\pgarappstp01\\Share\\turker.mp3";
			RecognitionAudio audio = RecognitionAudio.newBuilder()
					.setUri(uri)
					.build();
			RecognizeResponse response = speechClient.recognize(config, audio);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
