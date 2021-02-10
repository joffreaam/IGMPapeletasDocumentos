/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.utils;

import ec.gob.igm.cne.documentos.DatosJunta;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

// Agregado por Marco - JA
import java.nio.charset.Charset;


/**
 *
 * @author jkari
 */
public class Impresion {

    private static String titulo = "DOCUMENTOS ELECTORALES 2021";
    private static String etiquetaProvincia = "PROVINCIA:      ";
    private static String etiquetaCricunscripcion = "CIRCUNSCRIPCIÓN:";
    private static String etiquetaCanton = "CANTON:         ";
    private static String etiquetaParroquia = "PARROQUIA:      ";
    private static final String ETIQUETA_ZONA = "ZONA";
    private static final String DIA_SUFRAGIO_1RA_VUELTA =""; // "5 de febrero 2021";
    private static final String DIA_ESCRUTINIO_1RA_VUELTA = ""; // "7 de febrero 2021";
    private static final String DIA_SUFRAGIO_2DA_VUELTA = ""; //"9 de abril 2021";
    private static final String DIA_ESCRUTINIO_2DA_VUELTA = ""; //"11 de abril 2021";

    public static void acualizarEtiquetas(String tipoVoto, String escrutinioSufragio) {
        //MS - 28.01.2021 se agrega un condicienante para actualizar los labels previo a la impresión
        if (tipoVoto.toLowerCase().contains("general")) {
            etiquetaProvincia = "PROVINCIA:      ";
            etiquetaCricunscripcion = "CIRCUNSCRIPCIÓN:";
            etiquetaCanton = "CANTON:         ";
            etiquetaParroquia = "PARROQUIA:      ";
        }
        
        if (tipoVoto.toLowerCase().contains("exterior")) {
            etiquetaProvincia = "CIRCUNSCRIPCIÓN DEL EXTERIOR: ";
//            etiquetaCricunscripcion = "PAÍS:             ";
//            etiquetaCanton = "OFICINA CONSULAR: ";
            etiquetaCricunscripcion = "";
            etiquetaCanton = "PAÍS:             ";
            etiquetaParroquia = "OFICINA CONSULAR: ";
        }
        if (tipoVoto.toLowerCase().contains("casa") || tipoVoto.toLowerCase().contains("ppl")) {
            
            if (escrutinioSufragio != null){             
            
                if (escrutinioSufragio.equals("E")) {
                    titulo = "DOCUMENTOS DIA DEL ESCRUTINIO"; //"Día del Escrutinio, " + DIA_ESCRUTINIO_1RA_VUELTA;
                } else if (escrutinioSufragio.equals("S")) {
                    titulo = "DOCUMENTOS DIA DEL SUFRAGIO"; //"Día del Sufragio, " + DIA_SUFRAGIO_1RA_VUELTA;
                }
            }
        }
    }

    public static String cargarGrafico(String genero)
    {
        String zplCommand = null;
    if(genero.equals("MASCULINO")){
       zplCommand+="~~DGR:HOMBRE.GRF,00445,005,";
       zplCommand+="\n";
       zplCommand+="0001C000000003F000000007FC00000007FC0000001FFC0000003FFE0000003";
       zplCommand+="\n";
       zplCommand+="FFE0000003FFE0000003FFE0000003FFE0000003FFE0000001FFC0000000FFC";
       zplCommand+="\n";
       zplCommand+="0000000FFC00000007F800000000000000000000000003FFFFE00003FFFFF80";
       zplCommand+="\n";
       zplCommand+="007FFFFF80007FFFFFC000FFFFFFC000FFFFFFC001FFFFFFC001FFFFFFC001F";
       zplCommand+="\n";
       zplCommand+="FFFFFC001EFFFF9C001EFFFF9C001EFFFF9C001EFFFF9C001EFFFF9C001EFFF";
       zplCommand+="\n";
       zplCommand+="F9C001EFFFF9C001EFFFF9C001EFFFF9C001EFFFF9C001EFFFF9C001EFFFF9C";
       zplCommand+="\n";
       zplCommand+="001EFFFF9C001EFFFF9C001EFFFF9C001EFFFF9C001EFFFF9C001EFFFF9C001";
       zplCommand+="\n";
       zplCommand+="EFFFF9C001EFFFF9C001EFFFF9C001EFFFF9C001EFFFF9C000EFFFF9C0000FE";
       zplCommand+="\n";
       zplCommand+="3F800000FE3F800000FE3F800000FE3F800000FE3F800000FE3F800000FE3F8";
       zplCommand+="\n";
       zplCommand+="00000FE3F800000FE3F800000FE3F800000FE3F800000FE3F800000FE3F8000";
       zplCommand+="\n";
       zplCommand+="00FE3F800000FE3F800000FE3F800000FE3F800000FE3F800000FE3F800000F";
       zplCommand+="\n";
       zplCommand+="E3F800000FE3F800000FE3F800000FE3F800000FE3F800000FE3F800000FE3F";
       zplCommand+="\n";
       zplCommand+="800000FE3F800000FE3F800000FE3F800000FE3F800000FE3F800000FE3F800";
       zplCommand+="\n";
       zplCommand+="000FE3F800000FE3F800000FE3F8000003C0F0000003C060000000000000000";
       zplCommand+="\n";
       zplCommand+="00000000";
       zplCommand+="\n";
       zplCommand+="^FO720,150^XGR:HOMBRE.GRF,2,2^FS";
      
       //Comando para una imagen
       /*    zplCommand+="^XA\n" +
"^FX GF command parameters:\n" +
"^FX - format (A/B/C)\n" +
"^FX - dataBytes (number)\n" +
"^FX - totalBytes (number)\n" +
"^FX - rowBytes (number)\n" +
"^FX - image data (bytes)\n" +
"^FO33,40^GFA,1860,1860,15,,::X038,X0FE,X0FF,W01C3C,W0781F8,V0FF801E,S0181E6I07,S03838J03,S078FK038,S0FFCK01C,S0EF8002I0F,S0C0030F80078,R01C00FDFC0038,S0C039FFE001C,S0E070C070F8C,S0E06I03FF86,S0F0CI01E1C6,S0FB8L0C7,S0DF8001800C3,R01DDC003C00C3,S0DAJ0C01838,S0F8J0603038,S0FK0207038,S07M03878,S0E1L07C78,S0E38307003E7,S0E3830FC03F7,S0C38607C03F7,R01C30E00C03FE,J08M01C00C00406FE,I03FM01C018J07FE,I063M01C038J037C,I0C1M01C038J07BC,00181M01C038J02B8,00103M018038J0338,00103M01801CK038,00303M01CJ0CI018,00303M01C4I0EI01C,00303M01CE003F8001C,00303M01DFE3E38001C,00303N0DC3E0780038,00103N0EF003D800F8,001838M0E5C0F1807F,00181CM0E07FCI07E,003E0FM0700EJ0E,03FF878L07014I01E,0F03E3CL0383EI03C,1C0071CL03C1CI078,180038EL01CK0FC,180018F8K01EJ01FF8,18001C7F8K0FJ07E3F,18001C7NFCJ0C1FC,1FFE1C7BMFEJ0C19F,0FDFFC78I06001FC0018387C,0C00F87CL038FF870383E,1C00386CL038FF9E0700F8,1C001C4CL0387FF807003C,1CI0ICL0383FF00E001E,1E700CECL01C0F801CI0F,0IFJCL01CJ038I07C,07IFICM0EJ0FJ01E,060078C8M06I01EK0E,06003CC8M06I0F8K07,06001DD8J06006003EL038,07003FBK0600E00FM01C,03C0FB3K0E00E00CN0E,01IF66J03E00E008N07,00FCFECI03E600E018N038,007FFD8007F0600C018N01C,I0MF80600C018O0C,K03FFEI0E01C018O0E,R0E01C018O07,R0E01C018007L038R0E018038003CK038R0E018038I0FK01CR06018038I03EK0CR06018038J0FCJ0CR0601803K07FJ0CR0601803K0E7CI0CR0601803K0E1C0018R0601803K0E180018R0601803K0C3I01,R0601803J01C3I03,R0601803J01C6I03,R0603803J01C6I06,R0703803J01CCI06,R0701803J01CCI0C,R0701803J01D8I0C,R0781803J01980018,R07E1803J03B80018,R07F9803J03BI03,R03BF803J07FI03,R038F803I03FFI06,R0383003803IFI06,R038I03IFC3FI0C,R038J0FF803F801C,R038N03FE018,R038N03CF03,R01CN0387FF,R01CN0383FE,R01CN03803E,R01FN0F807F,R01FCL07F80E3,S0CFCJ07FF80E3,S0C1FF1KFC0DB,S0C03LF1C07B,S0CI0JF00C03F,S0CO0E006,S0EO0F00E,S0EO07FFC,S06O07BFC,S06O038,:S07O01C,:S02P0C,,::::^FS\n" +
"^XZ";*/
       }
        else{
        
      
       zplCommand+="~~DGR:MUJER.GRF,00540,006,";
  
       zplCommand+="\n";
       zplCommand+="00000000000000000000000000000000000000000000000000007F800000000";
       zplCommand+="\n";
       zplCommand+="0FFC000000001FFE000000001FFE000000001FFF000000001FFF000000001FF";
       zplCommand+="\n";
       zplCommand+="F000000001FFF000000001FFF000000001FFE000000000FFE000000000FFE00";
       zplCommand+="\n";
       zplCommand+="00000003F80000000001F0000000000000000000001FFE00000000FFFFC0000";
       zplCommand+="\n";
       zplCommand+="001FFFFE0000003FFFFF0000003FFFFF0000007FFFFF8000007FFFFFC00000F";
       zplCommand+="\n";
       zplCommand+="FFFFFC00000FFFFFFC00001FFFFFFE00001FDFFEFE00001FBFFF7E00001FBFF";
       zplCommand+="\n";
       zplCommand+="F7E00001FBFFF7F00003FBFFF7F00003F7FFFBF00007F7FFFBF80007F7FFF9F";
       zplCommand+="\n";
       zplCommand+="80007EFFFFDF80007EFFFFDF80007EFFFFDF80007EFFFFDF80007DFFFFCF800";
       zplCommand+="\n";
       zplCommand+="0FDFFFFEFC000FDFFFFEFC000FBFFFFF7C000FBFFFFF7C000F3FFFFF3C000F3";
       zplCommand+="\n";
       zplCommand+="FFFFF3C000F3FFFFF3C00067FFFFF9800007FFFFF800000FFFFFFC00000FFFF";
       zplCommand+="\n";
       zplCommand+="FFC00000FFFFFFC00001FFFFFFE00001FFFFFFE00001FFFFFFE00001FFFFFFE";
       zplCommand+="\n";
       zplCommand+="00003FFFFFFE00003FFFFFFF00003FFFFFFF0000003F3F000000003F3F00000";
       zplCommand+="\n";
       zplCommand+="0003F3F000000003F3F000000003F3F000000003F3F000000003F3F00000000";
       zplCommand+="\n";
       zplCommand+="3F3F000000003F3F000000003F3F000000003F3F000000003F3F000000003F3";
       zplCommand+="\n";
       zplCommand+="F000000003F3F000000003F3F000000003F3F000000003F3F000000003F3F00";
       zplCommand+="\n";
       zplCommand+="0000003F3F000000003F3F000000003F3F000000003F3F000000003F3F00000";
       zplCommand+="\n";
       zplCommand+="0003F3F000000003F3F000000003F3F000000001E1E00000000000000000000";
       zplCommand+="\n";
       zplCommand+="000000000";
       zplCommand+="\n";
       zplCommand+="^FO670,150^XGR:MUJER.GRF,2,2^FS";
                  }
        if(genero.equals("MASCULINO")){
            zplCommand += "^XA^CI28\n";
                   zplCommand+="^FO720,150^XGR:HOMBRE.GRF,2,2^FS";  
                }  
                else{
            zplCommand += "^XA^CI28\n";
            zplCommand+="^FO710,150^XGR:MUJER.GRF,2,2^FS";
                }
        
        return zplCommand;
    }
        
    
    /**
     * Imprime una etiqueta y escribe dtos en su chip RFID
     *
     * @param datosJunta información de la junta
     * @param tipoVoto indica el tipo de voto
     * @param escrutinioSufragio indica si corresponde a un paquete del día del
     * escrutinio o sufragio
     * @throws PrintException
     */
    public static void etiquetaDocumentos(DatosJunta datosJunta, String tipoVoto, String escrutinioSufragio) throws PrintException {
        titulo = "DOCUMENTOS ELECTORALES 2021";
        //MS - 28.01.2021 La actualización de los labels previo a la impresión van sin el la clausula "if"
        //if (escrutinioSufragio != null || tipoVoto.toLowerCase().contains("exterior")) {
            acualizarEtiquetas(tipoVoto, escrutinioSufragio);
        //}
        
        String zplCommand;
       
        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob job = printService.createPrintJob();
        String codigo = datosJunta.getBarras().replace("06", "113").substring(0, 20);
        zplCommand=Impresion.cargarGrafico(datosJunta.getSexo());
        String descripcionGenero=datosJunta.getSexo().equals("MASCULINO")?"MASCULINO":"FEMENINO";
        zplCommand += "^RR1^RFW,H^FD" + datosJunta.getTag().substring(1, 23) + "^FS";
        zplCommand+="\n";
        zplCommand+= "^WT,0,0,1^FS";
        zplCommand+="\n";
        zplCommand+= "^FO20,60^B3N,N,45,Y,N^FD" + datosJunta.getBarras() + "^FS";
        zplCommand+="\n";
        zplCommand+= "^FO70,130^A0N,50,30^ADN,25,17^FD " + titulo + "^FS";
        zplCommand+="\n";
        String auxProv=datosJunta.getProvincia();
        if(datosJunta.getProvincia().length()>25){
             auxProv = datosJunta.getProvincia().substring(0, 25)+".";
        }
        //zplCommand+= "^FO20,213^A0N,40,30^ADN,15,5^FD " + etiquetaProvincia + "" + datosJunta.getProvincia() + "^FS";
        zplCommand+= "^FO20,213^A0N,40,30^ADN,15,5^FD " + etiquetaProvincia + "" + auxProv + "^FS";
        zplCommand+="\n";
        zplCommand+= "^FO20,242^A0N,40,30^ADN,15,5^FD " + etiquetaCricunscripcion + "" + datosJunta.getCircunscripcion() + "^FS";
        zplCommand+="\n";
        zplCommand+= "^FO20,272^A0N,40,30^ADN,15,5^FD " + etiquetaCanton + "" + datosJunta.getCanton() + "^FS";
        zplCommand+="\n";
        
        // Add por Joffre A
        // siguiente linea comentada
        //zplCommand+= "^FO20,303^A0N,40,30^ADN,15,5^FD " + etiquetaParroquia + ":      " + datosJunta.getParroquia().replace('Ñ', 'N').replace('Á', 'A').replace('É', 'E').replace('Í', 'I').replace('Ó', 'O').replace('Ú', 'U') + "^FS";
        zplCommand+= "^FO20,303^A0N,40,30^ADN,15,5^FD " + etiquetaParroquia + "" + datosJunta.getParroquia() + "^FS";

        
        zplCommand+="\n";
        // Add Joffre A la parte de descripcionGenero
         String zona="";
        zona= datosJunta.getZona() == null?"":datosJunta.getZona();
        zplCommand+= "^FO20,332^A0N,40,30^ADN,15,5^FD " + ETIQUETA_ZONA + ":        " + zona  +   "^FS";
        // Add Joffre A  -MC        
        zplCommand+="\n";
        zplCommand+= "^FO20,362^A0N,40,30^ADN,15,5^FD NÚMERO JRV:     " + datosJunta.getJunta() + "      " + "^FS";
        zplCommand+= "^FO400,362^A0N,40,30^ADN,15,5^FD" + descripcionGenero  + "^FS";
        String juntaCeros = String.format("%4s",datosJunta.getJunta()).replace(' ','0');
        // Add por Marco - JA
        zplCommand+= "^FO670,362^A0N,60,30^ADN,38,17^FD " + juntaCeros + "      " + "^FS";
        //zplCommand+="\n";
        
        zplCommand+="\n";
        
        zplCommand+= "^XZ ";
        
        // add por Marco y JA
        // siguiente linea comentada
        //byte[] by = zplCommand.getBytes();
        byte[] by = zplCommand.getBytes(Charset.forName("UTF8"));
        
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(by, flavor, null);
        job.print(doc, null);
    }
}
