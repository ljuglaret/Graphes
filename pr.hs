{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE MultiParamTypeClasses #-}


import           Data.Graph.Inductive
import           Data.GraphViz
import           Data.GraphViz.Attributes.Complete
import           Data.GraphViz.Types.Generalised   as G
import           Data.GraphViz.Types.Monadic
import           Data.Text.Lazy                    as L
import           Data.Word

import Data.List as List

import           Control.Monad   (forM_)
import           System.FilePath

import Data.List
import Data.Set

type Attributes = [Attribute] 

doDots :: PrintDotRepr dg n => [(FilePath, dg n)] -> IO ()
doDots cases = forM_ cases createImage

createImage :: PrintDotRepr dg n => (FilePath, dg n) -> IO FilePath
createImage (n, g) = createImageInDir "./img" n Png g

createImageInDir :: PrintDotRepr dg n => FilePath -> FilePath -> GraphvizOutput -> dg n -> IO FilePath
createImageInDir d n o g = Data.GraphViz.addExtension (runGraphvizCommand Dot g) o (combine d n)




data Choix = Pile
            |Face deriving (Show)




type Proba  =  String


listChoix = [Pile,Face ]
  
  
formeNoeuds :: Int -> [(Int,Text)]
formeNoeuds x = List.map
                (\elt ->
                        if(elt == 0)
                        then (elt,pack "début")
                        else
                                if( elt  `mod`2 == 0)
                                then (elt,pack(show Face))
                                else (elt  ,pack(show Pile)))
                ([0..(2*x)])

formeLiens :: Int -> [(Int,Int,Text)]
formeLiens x = List.map
                    (\(a,b) -> (a,b, pack "0.5"))
                    (List.zip
                            (List.concatMap
                                (\elt -> List.replicate 2 elt)
                                [0..(x - 1 )])
                            [1..(2*x)]
                    )
                            
concatene ::  Gr L.Text L.Text
concatene  = mkGraph
                (formeNoeuds 3 )
                (formeLiens 3)
                
                --(1,pack ( show  Pile)),(2,pack ( show Face)), (3,pack ( show  Pile)) ,
                   -- (4,pack ( show  Face)),(5,pack ( show  Pile)) , (6,pack ( show  Face)) ]
                -- [(0,1,pack "0.5"),(0,2,pack"0.5"),(1,3,pack"0.5"),(1,4,pack"0.5"),(2,5,pack"0.5"),(2,6,pack"0.5")]
      


ex1Params :: GraphvizParams n L.Text L.Text () L.Text
ex1Params = nonClusteredParams { globalAttributes = ga
                                , fmtNode          = fn
                                , fmtEdge          = fe
                                }
    where   fn (_,l)   = [textLabel l]
            fe (_,_,l) = [ textLabel l]

            ga = [ GraphAttrs [ RankDir   FromLeft
                            ]
                , NodeAttrs  [ shape     Circle
                            , style     filled
                            ]
                
                ]


main :: IO ()
main = do
     doDots [ ("pr" , graphToDot ex1Params concatene) ]