package br.com.ocr;

import java.io.File;
import java.io.IOException;

import br.com.utils.MyUtil;

/**
 * To use this class, you have to install Tesseract.
 * 
 * I recommend installing it via the installer
 * made by UB-Mannheim which you can found at:
 * 
 * https://github.com/UB-Mannheim/tesseract/wiki
 * 
 * I recommend Tesseract version 4.0.0 or newer.
 * 
 * By default, Tesseract comes with the english language
 * pack. If you want to use another language, you should
 * download it via command-line or 
 * 
 * @author GuiDB
 */
public class TesseractOCR {
	
	private File imageName;
	private File outputBase;
	private String extension = "";
	private String language = "eng";
	private PageSegmentationMode psm = PageSegmentationMode._03_FULLY_AUTOMATIC_PAGE_SEGMENTATION_BUT_NO_OSD;
	private OCREngineModes oem = OCREngineModes._03_DEFAULT;
	
	/**
	 * Minimal parameters to instantiate TesseractOCR.
	 * 
	 * By default, unset values will be:
	 * - extension: "" (or txt)
	 * - language: "eng"
	 * - psm: "3"
	 * - oem: "3"
	 * 
	 * @param imageName the image to be "ocrized"
	 * @param outputBase the output 
	 */
	public TesseractOCR(File imageName, File outputBase) {
		this.imageName = imageName;
		this.outputBase = outputBase;
	}
	
	/**
	 * By default, unset values will be:
	 * - language: "eng"
	 * - psm: "3"
	 * - oem: "3"
	 * 
	 * @param imageName
	 * @param outputBase
	 * @param extension
	 */
	public TesseractOCR(File imageName, File outputBase, String extension) {
		this.imageName = imageName;
		this.outputBase = outputBase;
		this.language = language;
	}
	
	/**
	 * By default, unset values will be:
	 * - psm: "3"
	 * - oem: "3"
	 * 
	 * @param imageName
	 * @param outputBase
	 * @param extension
	 * @param language
	 */
	public TesseractOCR(File imageName, File outputBase, String extension, String language) {
		this.imageName = imageName;
		this.outputBase = outputBase;
		this.language = language;
	}
	
	/**
	 * * By default, unset values will be:
	 * - oem: "3"
	 * 
	 * @param imageName
	 * @param outputBase
	 * @param extension
	 * @param language
	 * @param psm
	 */
	public TesseractOCR(File imageName, File outputBase, String extension, String language,
			PageSegmentationMode psm) {
		
		this.imageName = imageName;
		this.outputBase = outputBase;
		this.language = language;
		this.psm = psm;
	}
	
	/**
	 * * By default, unset values will be:
	 * - psm: "3"
	 * 
	 * @param imageName
	 * @param outputBase
	 * @param extension
	 * @param language
	 * @param oem
	 */
	public TesseractOCR(File imageName, File outputBase, String extension, String language,
			OCREngineModes oem) {
		
		this.imageName = imageName;
		this.outputBase = outputBase;
		this.language = language;
		this.oem = oem;
	}
	
	/**
	 * All parameters to instantiate TesseractOCR.
	 * 
	 * @param imageName
	 * @param outputBase
	 * @param extension
	 * @param language
	 * @param psm
	 * @param oem
	 */
	public TesseractOCR(File imageName, File outputBase, String extension, String language,
			PageSegmentationMode psm, OCREngineModes oem) {
		
		this.imageName = imageName;
		this.outputBase = outputBase;
		this.language = language;
		this.psm = psm;
		this.oem = oem;
	}

	/**
	 * @return the imageName
	 */
	public File getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(File imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the outputBase
	 */
	public File getOutputBase() {
		return outputBase;
	}

	/**
	 * @param outputBase the outputBase to set
	 */
	public void setOutputBase(File outputBase) {
		this.outputBase = outputBase;
	}
	
	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the psm
	 */
	public PageSegmentationMode getPageSegmentationMode() {
		return psm;
	}

	/**
	 * @param psm the psm to set
	 */
	public void setPageSegmentationMode(PageSegmentationMode psm) {
		this.psm = psm;
	}

	/**
	 * @return the oem
	 */
	public OCREngineModes getOCREngineModes() {
		return oem;
	}

	/**
	 * @param oem the oem to set
	 */
	public void setOCREngineModes(OCREngineModes oem) {
		this.oem = oem;
	}

	/**
	 * Execute the "tesseract" command, using the attributes defined in this object.
	 * 
	 * Example (Windows): cmd /c tesseract imagename.jpg imagename -l eng --psm 3 --oem 3
	 */
	public void tesseract() {
		try {
			MyUtil.executeCommand(new ProcessBuilder("cmd", "/c",
					"tesseract",
					this.getImageName().getAbsolutePath(),
					this.getOutputBase().getAbsolutePath(),
					this.getExtension(),
					"-l", this.getLanguage(),
					"--psm", this.getPageSegmentationMode().getValue(),
					"--oem", this.getOCREngineModes().getValue()
			), true);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TesseractOCR t = new TesseractOCR(new File("C:\\Users\\guilh\\Downloads\\Print2.png"), new File("C:\\Users\\guilh\\Downloads\\Print2"));
		t.tesseract();
	}
	
}