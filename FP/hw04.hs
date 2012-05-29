module Hw04 where

-- 1. 
b x y z = x (y z) 
k x y = x 
s x y z = x z (y z) 
-- ����������� b ����� s � k.. 

b' x y z = s (k x) y z


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
ndig n = helper 0 n where
	helper acc n 
		| n == 0 = acc
		| otherwise = helper (acc + 1) (div n 10) 

-- b) �������� �������, ������������ ����� ���� �����. 
sumdig :: Integer -> Integer
sumdig n = helper 0 n where 
	helper acc n 
		| n == 0 = acc
		| otherwise = helper (acc + (mod n 10)) (div n 10) 

-- 6. ����������� ������� gcd ��� ������ ��������� �������. 

gcd' :: Integer -> (Integer -> Integer)
gcd' a b 
	| b == 0 = a
	| otherwise = gcd' b (mod a b) 

-- 7. ����������� �������, ��������� �������� ������������� ��������� �� ������� ������� ��������. 

intl :: (Float -> Float) -> (Float -> (Float -> Float))
intl f a b = helper 0 f a b where 
	helper acc f a b 
		| a < b = helper (acc + trap f a delta) f (a + delta) b 
		| otherwise = acc where
			trap f a delta' = ((f a) + (f (a + delta'))) / 2 * delta';
			delta = 0.0001
			
