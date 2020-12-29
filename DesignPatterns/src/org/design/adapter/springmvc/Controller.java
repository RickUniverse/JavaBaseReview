package org.design.adapter.springmvc;

/**
 * @author lijichen
 * @date 2020/8/13 - 21:39
 */
interface Controller {
}
class HttpController implements Controller{
    public void  doHttpHander(){
        System.out.println("HttpController");
    }
}
class SimpleController implements Controller{
    public void  doSimpleHander(){
        System.out.println("SimpleController");
    }
}
class AnnotationController implements Controller{
    public void  doAnnotionHander(){
        System.out.println("AnnotationController");
    }
}