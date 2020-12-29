package org.design.adapter.springmvc;

/**
 * @author lijichen
 * @date 2020/8/13 - 21:45
 */
interface HandlerAdapter {
    void handle(Controller handler);
    boolean supports(Controller handler);
}
class AnnotationAdapterHandler implements HandlerAdapter {

    @Override
    public void handle(Controller handler) {
        ((AnnotationController) handler).doAnnotionHander();
    }

    @Override
    public boolean supports(Controller handler) {
        return (handler instanceof AnnotationController);
    }
}
class HttpAdapterHandler implements HandlerAdapter {

    @Override
    public void handle(Controller handler) {
        ((HttpController) handler).doHttpHander();
    }

    @Override
    public boolean supports(Controller handler) {
        return (handler instanceof HttpController);
    }
}
class SimpleAdapterHandler implements HandlerAdapter {

    @Override
    public void handle(Controller handler) {
        ((SimpleController) handler).doSimpleHander();
    }

    @Override
    public boolean supports(Controller handler) {
        return (handler instanceof SimpleController);
    }
}
