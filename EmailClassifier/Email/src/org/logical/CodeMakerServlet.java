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
     
    //生成随机类
    Random random = new Random();
     
    //验证码字体
    private Font[] codeFont =
    {
        new Font("Times New Roman", Font.PLAIN,18), 
        new Font("Times New Roman", Font.PLAIN, 18),
        new Font("Times New Roman", Font.PLAIN,18),
        new Font("Times New Roman", Font.PLAIN, 18)
    };
    //验证码数字颜色
    private Color[] color =
    {
       Color.BLACK, Color.RED, Color.DARK_GRAY, Color.BLUE
    };
 
    String codeNumbers = "";
 
    int width = 60, height = 20;
 
    // 处理 HTTP get 请求
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                      throws ServletException, IOException
    {
       
        // 清空缓冲区
        response.reset();
 
        // 注意这里的MIME类型
        response.setContentType("image/png");
 
        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
 
        // 创建一个图像,验证码显示的图片大小
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
 
        // 获取图形上下文
        Graphics g = image.getGraphics();
         
        // 设置背景
        g.setColor(getRandColor(200,250));
        g.fillRect(0, 0, width, height);
 
        for (int i = 0; i < 4; i++)
        {
            drawCode(g, i);
        }
        //添加干扰线
        drawNoise(g, 12);
 
        // 将验证码内容保存进session中，用于验证用户输入是否正确时使用
        HttpSession session = request.getSession();
        session.removeAttribute("rand");
        session.setAttribute("rand", codeNumbers);
        // 重设字符串
        codeNumbers = "";
        // 利用ImageIO类的write方法对图像进行编码
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "PNG", sos);
        sos.close();
    }
 
    // 绘制验证码
    public void drawCode(Graphics graphics, int i)
    {
        String number = String.valueOf(random.nextInt(10));
        graphics.setFont(codeFont[i]);
        graphics.setColor(color[i]);
        //绘制验证码到图片X、Y
        graphics.drawString(number, 6 + i * 13,16);
 
        codeNumbers += number;
    }
 
    // 绘制干扰线
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
    public Color getRandColor(int fc,int bc){//给定范围获得随机颜色
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }
    // 处理 HTTP post 请求, 和doGet一样
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doGet(request, response);
    }
}
