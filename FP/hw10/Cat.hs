module Main where

import IO
import System
import qualified Control.Exception as E
import Control.Exception (IOException)

catFile :: String -> IO()
catFile f = do
    s <- readFile f
    putStr s

main :: IO()
main = do 
    args <- getArgs

    if (args == []) then 
        interact id
    else
        (E.catch (mapM_ catFile args) (\e -> do
                        let err = show (e :: IOException)
                        hPutStrLn stderr ("Error: " ++ err)))
                        