
class Monad m => MonadMaybe m where
    abort :: m a
    recover :: m a -> m a -> m a

instance  MonadMaybe Maybe where
    abort = Nothing
    recover Nothing b = b
    recover a _ = a
    
f1 = do
    Just 3
    Just 4
    
f2 = do
    Just 5
    
fuck = recover f1 f2
    
