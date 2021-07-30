import System.IO
import Data.List
import Text.Printf
import System.Directory
import System.CPUTime
import Control.DeepSeq
--读取目录下的文件    
readDir path = do
    setCurrentDirectory path
    _cd <- getCurrentDirectory
    _file <- getDirectoryContents _cd
    --print _file
    let res = [ _cd ++ "/" ++ file |file <- _file,take 6 file == "matrix"]
    return res

--处理每一个文件
dirProcess :: [String] -> IO()
--如果为空了，则表示处理完了
dirProcess [] = printf "process over\n"
dirProcess (xs:ys) = do
    --每个文件处理10次
    timesProcess 10 0.0 xs
    --递归处理
    dirProcess ys
    
    

--输入参数:重复计算次数、总耗用时间，文件名
timesProcess::Int->Double->String->IO()
--计算到最后一次了，直接输出平均值
timesProcess 0 allTime file = printf " | average_time: %.5fs\n" (allTime/10) 
--递归计算耗时时间总和
timesProcess times allTime file =  do
    interval <- fileProcess file
    timesProcess (times-1) (interval + allTime) file 
    

--对每个文件里的matrix处理
fileProcess::[Char]->IO Double
fileProcess file = do
    --读取文件内容，得到matrix
    matrixFile <- readFile file 
    let matLines = lines matrixFile
    let matrix = [map (read::String->Int) (words line) | line <- matLines]
    --print matrix

    st <- getCPUTime
    --开始进行矩阵计算
    let res = matrixProcess matrix matrix
    --因为haskell是惰性计算，要用deepseq进行严格计算
    ed <- res `deepseq` getCPUTime

    let diff = (fromIntegral (ed - st)) / (10^12)

    printf "%.5fs " diff
    return (diff::Double)

matrixProcess::[[Int]]->[[Int]]->[[Int]]
matrixProcess matrix1 matrix2 = [[sum (zipWith (*) a b) | b <- transpose matrix2 ]| a <- matrix1 ]

    
main = do
    --先得到目录文件
    xs <- readDir "../../data/"
    --逐一处理每个文件
    dirProcess xs

    

        
    
    
