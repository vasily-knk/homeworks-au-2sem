module Main where

import Control.Monad
import Data.Monoid
import Control.Monad.Writer

-- 1.
-- Напишите функцию for :: Monad m => Integer -> Integer -> (Integer -> m ()) -> m ()
-- так, чтобы for 0 5 (\i -> print i) выводил бы числа 0, 1, 2, 3, 4.
       
for :: Monad m => Integer -> Integer -> (Integer -> m ()) -> m ()
for x y f = mapM_ f [x..(y-1)]

        
    
-- 3.
-- a) Напишите монаду Counter. Она должна поддерживать операции
-- tick :: Counter ()
-- runCounter :: Counter a -> (a, Int) -- возвращает результат и количество тиков.


type Counter a = Writer (Sum Int) a 

counter :: (a, Int) -> Counter a
counter (a, i) = writer (a, Sum i)

runCounter :: Counter a -> (a, Int)
runCounter = helper . runWriter where
    helper (a, Sum i) = (a, i)

tick :: Counter ()
tick = counter ((), 1)

fac :: Int -> Counter Int
fac 0 = return 1    
fac n = do
  tick
  r <- fac (n - 1)
  return (n * r)
    
-- b) Напишите функции
-- filter' :: (a -> Bool) -> [a] -> Counter [a]

filter' :: (a -> Bool) -> [a] -> Counter[a]
filter' _ [] = return []
filter' pred (x:xs)
    | pred x = do
        tick
        xs <- filter' pred xs 
        return (x : xs)
    | otherwise = filter' pred xs

-- append :: [a] -> [a] -> Counter [a]
append :: [a] -> [a] -> Counter [a]
append [] ys = return ys
append (x:xs) ys = do
    tick
    xs <- append xs ys
    return (x : xs)


-- qsort :: Ord a => [a] -> Counter [a]
-- являющиеся аналогами функций filter, (++), sort, но считающие количество шагов.
-- Убедитесь, что список [8,4,12,2,6,10,14,1,3,5,7,9,11,13,15] сортируется быстрее, чем [1..15]