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
    
    (E.catch (mapM_ catFile args) (\e -> do
                        let err = show (e :: IOException)
                        hPutStr stderr (err)
                        return ()))    