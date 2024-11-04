// 本文件包含支持教科书第3.7节的材料：
// "面向对象软件工程"，并根据www.lloseng.com上的开源许可证发布

package edu.seg2105.client.common;

/**
 * 该接口实现了用于在客户端或服务器UI上显示对象的抽象方法。
 *
 * 作者：Dr Robert Lagani&egrave;re
 * 作者：Dr Timothy C. Lethbridge
 */
public interface ChatIF 
{
  /**
   * 当被重写时，该方法用于在UI上显示对象。
   */
  public abstract void display(String message);
}
