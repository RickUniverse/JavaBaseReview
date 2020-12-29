package org.design.adapter.springmvc;

import java.util.ArrayList;

/**
 * @author lijichen
 * @date 2020/8/13 - 21:45
 */
public class DispathServlet {
    private ArrayList<HandlerAdapter> handList = new ArrayList();

    public DispathServlet() {
        handList.add(new AnnotationAdapterHandler());
        handList.add(new HttpAdapterHandler());
        handList.add(new SimpleAdapterHandler());
    }

    public void doDispatch(){
        AnnotationController a = new AnnotationController();
//        HandlerAdapter handlerAdapter = getHandlerAdapter(a);
//        handlerAdapter.handle(a);
        HttpController h = new HttpController();
        HandlerAdapter handlerAdapter = getHandlerAdapter(h);
        handlerAdapter.handle(h);
    }

    public HandlerAdapter getHandlerAdapter(Controller c){
        for (HandlerAdapter handlerAdapter : handList) {
            if (handlerAdapter.supports(c)){
                return handlerAdapter;
            }
        }
        return null;
    }
}
class Cline {
    public static void main(String[] args) {
        new DispathServlet().doDispatch();
        HttpController s = new HttpController();
        Controller controller = new Controller(){};
        System.out.println((Object)s instanceof SimpleController);//false
        System.out.println((Object)controller instanceof SimpleController);//false
        System.out.println((Object)controller instanceof Controller);//true
        System.out.println(new SimpleController() instanceof SimpleController);//true
        System.out.println(new SimpleController() instanceof Controller);//true
    }

}