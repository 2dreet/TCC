/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author Jose
 */
public class ExportarXls {

    private List<Object[]> lista;
    private File arquivo;
    private int[] tamanhoColunas;

    public ExportarXls(String nomeArquivo) {
        this(nomeArquivo, null);
    }

    public ExportarXls(File arquivo) {
        this.arquivo = arquivo;
        lista = new ArrayList<Object[]>();
    }

    public ExportarXls(String nomeArquivo, String localArquivo) {
        super();
        try {
            if (nomeArquivo != null && !nomeArquivo.trim().isEmpty()) {
                if (localArquivo != null && !localArquivo.trim().isEmpty()) {
                    nomeArquivo = localArquivo + nomeArquivo;
                }
                arquivo = new File(nomeArquivo);
                lista = new ArrayList<Object[]>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getArquivo() {
        return arquivo;
    }

    public void addLinha(Object[] linha) {
        lista.add(linha);
    }

    public void setTamanhoColunas(int[] tamanhoColunas) {
        this.tamanhoColunas = tamanhoColunas;
    }

    public void exportarArquivo(String nomeAba) {
        try {
            if (lista != null && lista.size() > 0) {
                WritableFont fontHeader = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.WHITE);
                WritableCellFormat cellFormatHeader = new WritableCellFormat(fontHeader);
                cellFormatHeader.setBackground(Colour.GRAY_50);
                cellFormatHeader.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);
                cellFormatHeader.setAlignment(Alignment.CENTRE);

                WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
                WritableCellFormat cellFormat = new WritableCellFormat(font);
                cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);

                WritableCellFormat cellFormatDireita = new WritableCellFormat(font);
                cellFormatDireita.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);
                cellFormatDireita.setAlignment(Alignment.RIGHT);

                WritableWorkbook workbook = Workbook.createWorkbook(arquivo);
                WritableSheet sheet = workbook.createSheet(nomeAba, 0);

                //Preenche as celulas 
                for (int i = 0; i < lista.size(); i++) {
                    if (i % 2 == 0) {
                        cellFormat = new WritableCellFormat(font);
                        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);
                        cellFormat.setBackground(jxl.format.Colour.PALE_BLUE);
                        cellFormatDireita = new WritableCellFormat(font);
                        cellFormatDireita.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);
                        cellFormatDireita.setBackground(jxl.format.Colour.PALE_BLUE);
                    } else {
                        cellFormat = new WritableCellFormat(font);
                        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);
                        cellFormat.setBackground(jxl.format.Colour.WHITE);
                        cellFormatDireita = new WritableCellFormat(font);
                        cellFormatDireita.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);
                        cellFormatDireita.setBackground(jxl.format.Colour.WHITE);
                    }

                    Object[] coluna = lista.get(i);
                    for (int x = 0; x < coluna.length; x++) {
                        if (i == 0) {
                            sheet.addCell(new Label(x, i, String.valueOf(coluna[x]).replace("null", ""), cellFormatHeader));
                        } else if (coluna[x] instanceof Double) {
                            sheet.addCell(new Label(x, i, String.valueOf(coluna[x]).replace("null", ""), cellFormatDireita));
                        } else {
                            sheet.addCell(new Label(x, i, String.valueOf(coluna[x]).replace("null", ""), cellFormat));
                        }
                    }
                }

                //Ajusta o Tamanho das colunas
                if (tamanhoColunas != null && tamanhoColunas.length > 0) {
                    for (int i = 0; i < tamanhoColunas.length; i++) {
                        CellView cell = sheet.getColumnView(i);
                        if (tamanhoColunas[i] > 0) {
                            cell.setSize(tamanhoColunas[i]);
                        } else {
                            cell.setAutosize(true);
                        }
                        sheet.setColumnView(i, cell);
                    }
                } else {
                    for (int i = 0; i < sheet.getColumns(); i++) {
                        CellView cell = sheet.getColumnView(i);
                        cell.setAutosize(true);
                        sheet.setColumnView(i, cell);
                    }
                }

                workbook.write();
                workbook.close();
            }
        } catch (FileNotFoundException f) {
            f.printStackTrace();
            JOptionPane.showMessageDialog(null, "Arquivo está aberto! Feche o arquivo e tente novamente!!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao Exportar o Arquivo!\nEntre em contato com o Suporte!!");
        }
    }

}
