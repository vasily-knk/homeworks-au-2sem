module Fail
    ( Fail
    , abort
    , runFail
    ) where

-- Вычисления, которые могут завершатся неуспехом.
data Fail a = OK a | Fail deriving Show

instance Monad Fail where
    (>>=) Fail   _ = Fail
    (>>=) (OK a) f = f a
    return = OK
    
-- Остановить вычисления.
abort :: Fail a
abort = Fail

-- Возвращает Nothing, если вычисления завершились неуспехом, иначе Just <результат>.
runFail :: Fail a -> Maybe a
runFail Fail = Nothing
runFail (OK a) = Just a
