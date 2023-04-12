package it.poste.utils;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

import net.sf.sevenzipjbinding.IOutCreateArchiveZip;
import net.sf.sevenzipjbinding.IOutCreateCallback;
import net.sf.sevenzipjbinding.IOutItemZip;
import net.sf.sevenzipjbinding.ISequentialInStream;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.impl.OutItemFactory;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.impl.RandomAccessFileOutStream;

public class FileZipper {
	
	
    static class MyCreateCallback implements IOutCreateCallback<IOutItemZip> {
        
    	private final RandomAccessFile rafRead;
    	private final String sourceFileName;
    	private final long fileLen;

        public MyCreateCallback(RandomAccessFile rafRead, long fileLen, String sourceFileName) {
        	this.rafRead = rafRead;
        	this.fileLen = fileLen;
        	this.sourceFileName = sourceFileName;
		}

		public void setOperationResult(boolean operationResultOk) {
        }

        public void setTotal(long total) {
        }

        public void setCompleted(long complete) {
        }

        public IOutItemZip getItemInformation(int index,
                OutItemFactory<IOutItemZip> outItemFactory) {
            IOutItemZip outItem = outItemFactory.createOutItem();

            outItem.setDataSize(fileLen);
            outItem.setPropertyPath(sourceFileName);
            outItem.setPropertyCreationTime(new Date());

            return outItem;
        }

        public ISequentialInStream getStream(int index) {
        	
            return new RandomAccessFileInStream(rafRead);
        }
    }

    public boolean zipFile(File sourceFile, File zip) {

        String sourceFileName = sourceFile.getName();
        
        boolean res = false;
        

        RandomAccessFile raf = null;
        RandomAccessFile rafRead = null;
        IOutCreateArchiveZip outArchive = null;
        
        try {
        	
            raf = new RandomAccessFile(zip, "rw");
            rafRead = new RandomAccessFile(sourceFile, "r");
            
            outArchive = SevenZip.openOutArchiveZip();
            outArchive.setLevel(5);
            outArchive.createArchive(new RandomAccessFileOutStream(raf), 1,
                    new MyCreateCallback(rafRead, sourceFile.length(), sourceFileName));
            
            res = true;
            System.out.println("Compressione avvenuta con successo");
            
        } catch (Exception e) {
        	System.out.println("Errore genererico durante la compressione del file");
        	e.printStackTrace();
        } finally {
            if (outArchive != null) {
                try {
                    outArchive.close();
                } catch (IOException e) {
                	System.out.println("errore durante la chiusura dell'archivio");
                	e.printStackTrace();
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                	System.out.println("errore durante la chiusura del file");
                	e.printStackTrace();
                }
            }
            
            if (rafRead != null) {
                try {
                    rafRead.close();
                } catch (IOException e) {
                	System.out.println("errore durante la chiusura del file");
                	e.printStackTrace();
                }
            }
        }
        return res;
    }
    
}