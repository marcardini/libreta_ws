package com.libretaDigital.fileupload;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class FileUtilities {

	private static final String CATALINA_HOME = System.getenv("CATALINA_HOME");

	public static void copyFile(File sourceFile, String uploadDir) throws IOException {


		String uploadDirectory = CATALINA_HOME.replace("\\", "/")+uploadDir ;

		try {
			File targetFile = new File(uploadDirectory, sourceFile.getName());
			InputStream in = new FileInputStream(sourceFile);

			OutputStream out = new FileOutputStream(targetFile);

			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException ex) {
			throw new IOException(ex.getMessage());
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
	}

	public static void deleteFile(String directory, String file) throws IOException {
		File fileToDelete = new File(directory, file);
		if (!fileToDelete.isFile()) {
			throw new IOException("Debe ser un archivo");
		} else if (!fileToDelete.canWrite()) {
			throw new IOException("Debe tener permiso de escritura");
		} else {
			fileToDelete.delete();
		}
	}

	public static File getFile(String fileName) {
		String path = FileUtilities.class.getClassLoader().getResource(fileName).getPath();
		return new File(path);
	}


	public static String getUpdateInfoExtraData(String separator, boolean updateInfo, String result, boolean separatorAtBeginning, boolean separatorAtEnding) {
		
		if (separatorAtBeginning)
			result += separator;
		if (updateInfo)
			result += "S" ;
		else
			result += "N" ;
		
		if (separatorAtEnding)
			result += separator;
		
		return result;
	}

	// se crea un nuevo archivo y cada linea se escribe el string pasado por parametro. Se retorna la url del archivo subido
	public static String addParametersToFile(String urlString, String folderTempPath, String httpAddressAndPort, String extraInfo){ 
		URL url;
		File newFile = null;
		try {
			url = new URL(urlString);
			File originalFile = new File(url.getPath());

			newFile = new File(folderTempPath + originalFile.getName() + "_new.upload");

			BufferedReader bufferedReader = null;

			try {
				bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
				BufferedWriter bufferedWriter = null;
				try {
					bufferedWriter = new BufferedWriter(new FileWriter(newFile));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				String line = null;

				try {
					while ((line = bufferedReader.readLine()) != null) {

						if (!line.trim().isEmpty()) {
							String newLine = line.concat("," + extraInfo);
							try {
								bufferedWriter.write(newLine);
								bufferedWriter.newLine();
							} catch (IOException e) {
								System.out.println("Error al escribir el nuevo archivo. Linea: " + newLine);
							}
						}

					}
				} catch (IOException e) {
					System.out.println("Error al leer el archivo original");
				} finally {
					try {
						bufferedReader.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						bufferedWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (MalformedURLException e2) {
				System.out.println("Error al crear el url: " + urlString);
			} 


			return newFile.getAbsolutePath();
			
			} catch (IOException e) {
				e.printStackTrace();
			}

			

		return null;
	}
}