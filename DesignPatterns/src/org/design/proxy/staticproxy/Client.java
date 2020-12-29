package org.design.proxy.staticproxy;

/**
 * @author lijichen
 * @date 2020/8/15 - 19:32
 */
public class Client {
    public static void main(String[] args) {
        //使用代理类
        //创建代理类，传入具体实例
        TeacherProxy teacherProxy = new TeacherProxy(new Teacher());
        teacherProxy.teach();
    }
}
//接口，代理类和被代理类都需要实现
interface ITeacherDao {
    void teach();
}
//具体老师
class Teacher implements ITeacherDao {

    @Override
    public void teach() {
        System.out.println("老师在教课");
    }
}
//代理类：proxy
class TeacherProxy implements ITeacherDao {

    //将Iteacher到聚合到代理类
    private ITeacherDao iTeacherDao;

    public TeacherProxy(ITeacherDao iTeacherDao) {
        this.iTeacherDao = iTeacherDao;
    }

    @Override
    public void teach() {
        System.out.println("使用代理类开始代理！");
        iTeacherDao.teach();
        System.out.println("end");
    }
}