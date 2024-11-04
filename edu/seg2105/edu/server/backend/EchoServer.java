package edu.seg2105.edu.server.backend;
// 本文件包含支持教科书第3.7节的材料：
// "面向对象的软件工程"，并根据www.lloseng.com上的开源许可证发布


import ocsf.server.*;

/**
 * 这个类重写了抽象超类中的一些方法，以便为服务器提供更多功能。
 *
 * @作者 Dr Timothy C. Lethbridge
 * @作者 Dr Robert Lagani&egrave;re
 * @作者 Fran&ccedil;ois B&eacute;langer
 * @作者 Paul Holden
 */
public class EchoServer extends AbstractServer 
{
  //类变量 *************************************************
  
  /**
   * 默认监听端口。
   */
  final public static int DEFAULT_PORT = 5555;
  
  //构造函数 ****************************************************
  
  /**
   * 构造一个回声服务器实例。
   *
   * @param port 要连接的端口号。
   */
  public EchoServer(int port) 
  {
    super(port);
  }

  
  //实例方法 ************************************************
  
  /**
   * 这个方法处理从客户端收到的任何消息。
   *
   * @param msg 从客户端收到的消息。
   * @param client 消息来源的连接。
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
    System.out.println("收到消息: " + msg + " 来自 " + client);
    this.sendToAllClients(msg);
  }
    
  /**
   * 这个方法重写了超类中的方法。当服务器开始监听连接时调用。
   */
  protected void serverStarted()
  {
    System.out.println
      ("服务器正在监听端口 " + getPort() + " 上的连接。");
  }
  
  /**
   * 这个方法重写了超类中的方法。当服务器停止监听连接时调用。
   */
  protected void serverStopped()
  {
    System.out.println
      ("服务器已停止监听连接。");
  }
  
  
  //类方法 ***************************************************
  
  /*
   * 这个方法负责创建服务器实例（此阶段没有用户界面）。
   *
   * @param args[0] 要监听的端口号。如果没有输入参数，则默认为5555。
   */
  public static void main(String[] args) 
  {
    int port = 0; //监听的端口

    try
    {
      port = Integer.parseInt(args[0]); //从命令行获取端口
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //设置端口为5555
    }
  
    EchoServer sv = new EchoServer(port);
    
    try 
    {
      sv.listen(); //开始监听连接
    } 
    catch (Exception ex) 
    {
      System.out.println("错误 - 无法监听客户端！");
    }
  }
}
// EchoServer类结束
