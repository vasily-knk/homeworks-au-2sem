{-# LANGUAGE FlexibleContexts #-}

import Control.Monad.State
import Control.Monad.Reader

forState :: (MonadState s m, Num s, Ord s) => s -> s -> m () -> m ()
forState i j m 
    | i < j = do
        put i
        m
        forState (i+1) j m
    | otherwise = return ()

    
forReader :: (MonadReader s m, Num s, Ord s) => s -> s -> m () -> m ()
forReader  i j m 
    | i < j = local (\_ -> i) $ do
        m
        forReader (i+1) j m
    | otherwise = return ()

main = do
    flip runStateT (error "foo") $ forState 0 5 $ do
        i <- get
        liftIO $ putStr (show i ++ " ")
    flip runReaderT (error "bar") $ forReader 5 10 $ do
        i <- ask
        liftIO $ putStr (show i ++ " ")
    putStrLn ""
