package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins="*")
public class TourismController{
	@Autowired
	TourismRepo tourismRepo;
	
	@GetMapping("/tourism/find")
	public Tourism findById(@RequestParam int id) {
		
		
		Tourism tourism =tourismRepo.findById(id).get();
		 
		tourism.setImage(decompressBytes(tourism.getImage()));
		 
		 return tourism;
		
	}
	
	@PostMapping("/tourism/add")
	public String addProduct(@RequestParam ("dietFile") MultipartFile myFile,
			String name,
			String location,
			String des,
			String phone) {
		
		try {
			Tourism prdImage = new Tourism(name,location,des,phone,
					compressBytes(myFile.getBytes()));
			tourismRepo.save(prdImage);		
		}catch(Exception e) {
			
		}
		
		
		
		return "Successfully Added New Product";
		
	}
	
	@GetMapping("/tourism/delete")
	public List<Tourism> deleteTourism(@RequestParam int id){
		
		tourismRepo.deleteById(id);
		
		return getAllProducts();
	}
	@GetMapping("/tourism/all")
	public List<Tourism> getAllProducts(){
		
		List<Tourism> drList = new ArrayList<Tourism>();
		
		List<Tourism> resDrList = tourismRepo.findAll();
		Tourism tourism = null;
		for(int i=0;i<resDrList.size();i++) {
			
			tourism = resDrList.get(i);
			
			tourism.setImage(decompressBytes(tourism.getImage()));
			
			drList.add(tourism);
			
		}
		
		
		return drList;
	}
	
			public static byte[] compressBytes(byte[] data) {
				Deflater deflater = new Deflater();
				deflater.setInput(data);
				deflater.finish();

				ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
				byte[] buffer = new byte[1024];
				while (!deflater.finished()) {
					int count = deflater.deflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				try {
					outputStream.close();
				} catch (IOException e) {
				}
				System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

				return outputStream.toByteArray();
			}

			public static byte[] decompressBytes(byte[] data) {
				Inflater inflater = new Inflater();
				inflater.setInput(data);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
				byte[] buffer = new byte[1024];
				try {
					while (!inflater.finished()) {
						int count = inflater.inflate(buffer);
						outputStream.write(buffer, 0, count);
					}
					outputStream.close();
				} catch (IOException ioe) {
				} catch (DataFormatException e) {
				}
				return outputStream.toByteArray();
			}


}