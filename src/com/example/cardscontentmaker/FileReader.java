package com.example.cardscontentmaker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import android.content.res.Resources;

public class FileReader {

	public static String Read(Resources resources, int id) throws UnsupportedEncodingException{
		InputStream inputStream = resources.openRawResource(id);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int i;
		try {
			i = inputStream.read();
			while (i != -1)
			{
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String content = byteArrayOutputStream.toString("utf8"); //cp1251
		return content;
	}
}
