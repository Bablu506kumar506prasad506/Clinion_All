package Clinion_Execution;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Clinion_EDC_Study.AnnotationCRFBookExtraction;
import Clinion_EDC_Study.AttachmentsExtraction;
import Clinion_EDC_Study.CRFBookExtraction;
import Clinion_EDC_Study.CRFDataExtraction;
import Clinion_EDC_Study.MedicalcodingExtraction;
import Clinion_EDC_Study.NotesExtraction;
import Clinion_EDC_Study.QRSExtraction;
import Clinion_GlobalMethod.GlobelMethods;
import Clinion_StudyConfiguration.CentralAdminTasksRolesManagement;
import Clinion_StudyConfiguration.forgotpasswordClass;
import jxl.Sheet;
import jxl.Workbook;

public class Clinion_EDCStudyExecutionClass {
	
	@BeforeMethod
	public static void BraoserInit() throws Exception{
		
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("Login_Data");
		
		String url_link = st.getCell(0, 3).getContents();
		String BrowaerName_Data = st.getCell(1, 2).getContents();
		
		GlobelMethods.LauncherBrowser(BrowaerName_Data, url_link);
		
	}
	
	/*@Test (priority=0)
	public static void forgotpassword_Methd() throws Exception{
		forgotpasswordClass fr = new forgotpasswordClass();
		fr.forgotpasswordM();
	}
	
	@Test
	public void eCRFDataExtraction() throws Exception {
		CRFDataExtraction eCRFDE = new CRFDataExtraction();
		eCRFDE.eCRFDataExtraction_PanelWise();
		eCRFDE.eCRFDataExtraction_PageWise();
		eCRFDE.eCRFDataExtraction_LabPage();
		eCRFDE.eCRFDataExtraction_DashBORD();
		eCRFDE.eCRFDataExtraction_Editcheck();
		eCRFDE.eCRFDataExtraction_PerVisit();
		eCRFDE.eCRFDataExtraction_PerSubjt();
	}
	@Test
	public void NoteExtraction_Method() throws Exception {
		NotesExtraction NE = new NotesExtraction();
		NE.NoteExtraction_PerSubject();
		NE.NoteExtraction_PanelNote();
		NE.NoteExtraction_PageNote();
		NE.NoteExtraction_VisitNote();
	}
	@Test
	public void Attachment() throws Exception {
		AttachmentsExtraction att = new AttachmentsExtraction();
		att.Attachment_Single();
		att.Attachment_All();
	}
	
	@Test
	public void MedicalCodingExtraction_Metd() throws Exception{
		MedicalcodingExtraction mc = new MedicalcodingExtraction();
		mc.MedicalCodngEctrct_AE();
		mc.MedicalCodngEctrct_CM();
	}
	
	@Test
	public void CRFBookExtraction_methd() throws Exception{
		CRFBookExtraction book = new CRFBookExtraction();
		book.BookExtraction_WithFilter();
		book.BookExtraction_All();
	}
	
	@Test
	public void AnnotationCRFBookExtraction_methd() throws Exception{
		AnnotationCRFBookExtraction book = new AnnotationCRFBookExtraction();
		book.AnnotationCRFBookExtraction_WithFilter();
		book.AnnotationCRFBookExtraction_All();
	}
	
	@Test
	public void QRSExtraction_Methd() throws Exception{
		QRSExtraction QRS = new QRSExtraction();
		QRS.QRSExtraction_WithFilter();
		QRS.QRSExtraction_All();
	}*/
	
}
