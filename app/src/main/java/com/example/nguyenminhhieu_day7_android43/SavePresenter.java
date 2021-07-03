package com.example.nguyenminhhieu_day7_android43;

public class SavePresenter {
    ISave iSave;

    public SavePresenter(ISave iSave) {
        this.iSave = iSave;
    }

    public void onSave(int s){
        if(s == 1){
            iSave.onSaveSuccessful("Lưu thành công!");
        }else if(s == 2){
            iSave.onNotSave("Thông tin không được lưu!");
        }
    }
}
