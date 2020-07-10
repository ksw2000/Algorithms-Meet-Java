public abstract class ArrayData {
	public int [] A;
	public abstract int max();
	public abstract int min();
}
/*
    計算陣列中最大最小值
    這題主要是給我們熟悉評分環境用的
    我很認真的去熟悉了

    這故事要
    getList()
        利用資料夾路徑取得資料夾檔案
        配合遞迴可以抓取助教伺服器的檔案名稱
        這也是攻破助教電腦的第一步
        因為不確定助教電腦是用 Linux 還是 windows 所以資料夾路徑分隔符是當天抓
*/
