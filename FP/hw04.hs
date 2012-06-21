module Hw04 where

-- 1. 
b x y z = x (y z) 
k x y = x 
s x y z = x z (y z) 
-- ����������� b ����� s � k.. 

b' x = s (k x)


-- 2. 
plus = (+) 
-- �������� mult (���������) ����� plus. 
-- �������� expt (���������� � �������) ����� mult. 


mult :: Integer -> (Integer -> Integer)
mult a b 
	| a < 0 = multpos (-a) (-b) 
	| otherwise = multpos a b where
		multpos a b 
			| a == 0 = 0
			| otherwise = plus (multpos (a-1) b) b 

expt :: Integer -> (Integer -> Integer)
expt a b 
	| b > 0 = mult a (expt a (b-1)) 
	| otherwise = 1


-- 3. ����������� ��������� ������� http://en.wikipedia.org/wiki/McCarthy_91_function 
mc :: Integer -> Integer
mc n 
	| n > 100 = n - 10 
	| otherwise = mc (mc (n + 11))

-- 4. ����������� �������� ����������� minp :: (Integer -> Bool) -> Integer 

minp :: (Integer -> Bool) -> Integer 
minp p = helper p 0	where 
	helper p n
		| p n = n 
		| otherwise = helper p (n + 1)

-- 5. 
-- a) �������� �������, ������������ ���������� ���� �����.
        
ndig :: Integer -> Integer
ndig n
    | n == 0 = 0
    | otherwise = 1 + ndig (div n 10)
        

-- b) �������� �������, ������������ ����� ���� �����. 
sumdig :: Integer -> Integer
sumdig n
    | n == 0 = 0
    | otherwise = (mod n 10) + sumdig (div n 10)

-- 6. ����������� ������� gcd ��� ������ ��������� �������. 

gcd' :: Integer -> (Integer -> Integer)
gcd' a b 
	| b == 0 = a
	| otherwise = gcd' b (mod a b) 

-- 7. ����������� �������, ��������� �������� ������������� ��������� �� ������� ������� ��������. 

intl :: (Float -> Float) -> Float -> Float -> Float
intl f a b 
    | a < b = (intl f (a + delta) b) + (trap f a delta)
    | otherwise = 0 
    where
        delta = 0.0001;
        trap f a delta' = ((f a) + (f (a + delta'))) / 2 * delta'

