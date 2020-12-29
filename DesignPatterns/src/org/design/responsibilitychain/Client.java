package org.design.responsibilitychain;

/**
 * 责任链模式
 * @author lijichen
 * @date 2020/8/19 - 15:52
 */
public class Client {
    public static void main(String[] args) {
        //开始使用
        //创建请求者
        PurchaseRequest request = new PurchaseRequest("请求", 31090, 12);
        //创建处理者
        DepartmentApprover departmentApprover = new DepartmentApprover("系");
        CollegeApprover collegeApprover = new CollegeApprover("院");
        ViceApprover viceApprover = new ViceApprover("副");
        SchoolMasterApprover schoolMasterApprover = new SchoolMasterApprover("正");
        //修改每个的下一级审批者
        departmentApprover.setApprover(collegeApprover);//系的下一个审批者是院
        collegeApprover.setApprover(viceApprover);//院的下一个是副
        viceApprover.setApprover(schoolMasterApprover);//副的下一个是正
        schoolMasterApprover.setApprover(departmentApprover);//形成环形，正的下一级是系

        //开始审批，先使用系
        departmentApprover.processRequest(request);
    }
}
//请求者
class PurchaseRequest {
    private String type;
    private float price;
    private int id;

    public PurchaseRequest(String type, float price, int id) {
        this.type = type;
        this.price = price;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}
//审批者抽象类
abstract class Approver {
    //设置下一个审批的人
    Approver approver;
    String name;//审批人名

    public Approver(String name) {
        this.name = name;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    //审批方法,传入需要审批的对象
    protected abstract void processRequest(PurchaseRequest purchaseRequest);
}
//具体的审批者,系
class DepartmentApprover extends Approver {

    public DepartmentApprover(String name) {
        super(name);
    }

    @Override
    protected void processRequest(PurchaseRequest purchaseRequest) {
        //如果小于等于1000，则直接审批
        if (purchaseRequest.getPrice() <= 1000) {
            System.out.println(this.name + "允许，审批成功！金额为：" + purchaseRequest.getPrice()+"类型为："+purchaseRequest.getType()+"编号为："+purchaseRequest.getId());
        } else {
            System.out.println(this.name + "不可以处理此金额");
            //否则找到下一个审批者，接着审批
            super.approver.processRequest(purchaseRequest);
        }
    }
}
//具体的审批者,院
class CollegeApprover extends Approver {

    public CollegeApprover(String name) {
        super(name);
    }

    @Override
    protected void processRequest(PurchaseRequest purchaseRequest) {
        //如果小于等于1000，则直接审批
        if (purchaseRequest.getPrice() > 1000 && purchaseRequest.getPrice() <= 10000) {
            System.out.println(this.name + "允许，审批成功！金额为："
                    + purchaseRequest.getPrice()+"类型为："+purchaseRequest.getType()
                    + "编号为："+purchaseRequest.getId());
        } else {
            System.out.println(this.name + "不可以处理此金额");
            //否则找到下一个审批者，接着审批
            super.approver.processRequest(purchaseRequest);
        }
    }
}
//具体的审批者,副校长
class ViceApprover extends Approver {

    public ViceApprover(String name) {
        super(name);
    }

    @Override
    protected void processRequest(PurchaseRequest purchaseRequest) {
        //如果小于等于1000，则直接审批
        if (purchaseRequest.getPrice() > 10000 && purchaseRequest.getPrice() <= 30000) {
            System.out.println(this.name + "允许，审批成功！金额为："
                    + purchaseRequest.getPrice()+"类型为："+purchaseRequest.getType()
                    + "编号为："+purchaseRequest.getId());
        } else {
            System.out.println(this.name + "不可以处理此金额");
            //否则找到下一个审批者，接着审批
            super.approver.processRequest(purchaseRequest);
        }
    }
}
//具体的审批者,副校长
class SchoolMasterApprover extends Approver {

    public SchoolMasterApprover(String name) {
        super(name);
    }

    @Override
    protected void processRequest(PurchaseRequest purchaseRequest) {
        //如果小于等于1000，则直接审批
        if (purchaseRequest.getPrice() > 30000) {
            System.out.println(this.name + "允许，审批成功！金额为："
                    + purchaseRequest.getPrice()+"类型为："+purchaseRequest.getType()
                    + "编号为："+purchaseRequest.getId());
        } else {
            System.out.println(this.name + "不可以处理此金额");
            //否则找到下一个审批者，接着审批
            super.approver.processRequest(purchaseRequest);
        }
    }
}