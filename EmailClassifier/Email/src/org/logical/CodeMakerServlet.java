package org.logical;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
 
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CodeMakerServlet extends HttpServlet
{
      /**
     * 
     */
    private static final long serialVersionUID = 1L;
     
    //���������
    Random random = new Random();
     
    //��֤������
    private Font[] codeFont =
    {
        new Font("Times New Roman", Font.PLAIN,18), 
        new Font("Times New Roman", Font.PLAIN, 18),
        new Font("Times New Roman", Font.PLAIN,18),
        new Font("Times New Roman", Font.PLAIN, 18)
    };
    //��֤��������ɫ
    private Color[] color =
    {
       Color.BLACK, Color.RED, Color.DARK_GRAY, Color.BLUE
    };
 
    String codeNumbers = "";
 
    int width = 60, height = 20;
 
    // ���� HTTP get ����
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                      throws ServletException, IOException
    {
       
        // ��ջ�����
        response.reset();
 
        // ע�������MIME����
        response.setContentType("image/png");
 
        // ����ҳ�治����
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
 
        // ����һ��ͼ��,��֤����ʾ��ͼƬ��С
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
 
        // ��ȡͼ��������
        Graphics g = image.getGraphics();
         
        // ���ñ���
        g.setColor(getRandColor(200,250));
        g.fillRect(0, 0, width, height);
 
        for (int i = 0; i < 4; i++)
        {
            drawCode(g, i);
        }
        //��Ӹ�����
        drawNoise(g, 12);
 
        // ����֤�����ݱ����session�У�������֤�û������Ƿ���ȷʱʹ��
        HttpSession session = request.getSession();
        session.removeAttribute("rand");
        session.setAttribute("rand", codeNumbers);
        // �����ַ���
        codeNumbers = "";
        // ����ImageIO���write������ͼ����б���
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "PNG", sos);
        sos.close();
    }
 
    // ������֤��
    public void drawCode(Graphics graphics, int i)
    {
        String number = String.valueOf(random.nextInt(10));
        graphics.setFont(codeFont[i]);
        graphics.setColor(color[i]);
        //������֤�뵽ͼƬX��Y
        graphics.drawString(number, 6 + i * 13,16);
 
        codeNumbers += number;
    }
 
    // ���Ƹ�����
    public void drawNoise(Graphics graphics, int lineNumber)
    {
        graphics.setColor(getRandColor(160,200));
        for (int i = 0; i < lineNumber; i++)
        {
            int pointX1 = 1 + (int)(Math.random() * width);
            int pointY1 = 1 + (int)(Math.random() * height);
            int pointX2 = 1 + (int)(Math.random() * width);
            int pointY2 = 1 + (int)(Math.random() * height);
            graphics.drawLine(pointX1, pointY1, pointX2, pointY2);
        }
    }
    public Color getRandColor(int fc,int bc){//������Χ��������ɫ
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }
    // ���� HTTP post ����, ��doGetһ��
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doGet(request, response);
    }
}
