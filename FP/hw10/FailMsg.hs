module FailMsg
    ( FailMsg
    , abortMsg
    , runFailMsg
    ) where

-- Вычисления, которые могут завершатся неуспехом с сообщением об ошибке.
data FailMsg e a = OK a | Fail e deriving Show

instance Monad (FailMsg e) where
    (>>=) (Fail e) _ = Fail e
    (>>=) (OK a)   f = f a
    return = OK

-- abortMsg e остановливает вычисления с сообщением e.
abortMsg :: e -> FailMsg e a
abortMsg = Fail

-- Запускает вычисления. Возвращает Left <сообщение об ошибке>, либо Right <результат>.
runFailMsg :: FailMsg e a -> Either e a
runFailMsg (Fail e) = Left e
runFailMsg (OK a)   = Right a
