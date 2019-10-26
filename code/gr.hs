{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE MultiParamTypeClasses #-}


import           Data.Graph.Inductive
import           Data.GraphViz
import           Data.GraphViz.Attributes.Complete
import           Data.GraphViz.Types.Generalised   as G
import           Data.GraphViz.Types.Monadic
import           Data.Text.Lazy                    as L
import           Data.Word

import           Control.Monad   (forM_)
import           System.FilePath

import Data.List
import Data.Set

type Attributes = [Attribute] 

doDots :: PrintDotRepr dg n => [(FilePath, dg n)] -> IO ()
doDots cases = forM_ cases createImage

createImage :: PrintDotRepr dg n => (FilePath, dg n) -> IO FilePath
createImage (n, g) = createImageInDir "./tmp" n Jpeg g

createImageInDir :: PrintDotRepr dg n => FilePath -> FilePath -> GraphvizOutput -> dg n -> IO FilePath
createImageInDir d n o g = Data.GraphViz.addExtension (runGraphvizCommand Circo g) o (combine d n)




data Choix = Pierre 
            |Feuille
            | Ciseaux
            | Lezard
            | Spock  deriving (Show)




data PointFort  = Casse 
              |Coupent 
              |Enveloppe  
              |Repousse 
              |Ecrabouille 
              |Ecrase 
              |Empoisonne  
              |Detruit  
              |Mange 
              |Decapitent deriving(Show)


listChoix = [Pierre ,Feuille, Ciseaux,Lezard,Spock]
   
  

numero c = 
        case c of 
                Pierre -> 0
                Feuille ->1
                Ciseaux->2
                Lezard->3
                Spock -> 4


associe2 ::  [(Int,Int, PointFort)]
associe2 = 
        Data.List.map(\(a,b,elt) -> (numero a, numero b, elt))[
          (Ciseaux,Feuille, Coupent),
          (Feuille,Pierre, Enveloppe),
          (Pierre, Ciseaux,  Casse),
          (Lezard,Spock,  Empoisonne),
          (Spock,Ciseaux,  Ecrabouille),
          (Ciseaux,Lezard,  Decapitent),
          (Lezard,Feuille,  Mange),
          (Feuille,Spock, Repousse),
          (Spock,Pierre , Detruit),
          (Pierre,Lezard, Ecrase)

        ]


concatene ::  Gr L.Text L.Text
concatene  = 
        let
                listTaill =[0.. ((Data.List.length listChoix) - 1 )]
                zipl = Data.List.zip listTaill (Data.List.map(\elt -> pack(show elt)) listChoix )
                fl = Data.Set.toList (Data.Set.fromList(Data.List.map(\(x,y,z) -> (x,y,pack (show z))) associe2))
        in mkGraph zipl fl

im x = 
        case x of 
                "Lezard" -> Image "Lezard.png"
                "Ciseaux" -> Image "Ciseaux.png"
                "Spock" -> Image "spok.png"
                "Pierre" -> Image "Pierre.png"
                "Feuille" -> Image "Feuille.png"
                _ -> Image "augLum.png"

ex1Params :: GraphvizParams n L.Text L.Text () L.Text
ex1Params = nonClusteredParams { globalAttributes = ga
                                , fmtNode          = fn
                                , fmtEdge          = fe
                                }
    where   fn (_,l)   = [im l]
            fe (_,_,l) = [ textLabel l]

            ga = [ GraphAttrs [ RankDir   FromLeft
                            ]
                , NodeAttrs  [ shape    DoubleCircle
                            , style     filled
                            ]
                
                ]


main :: IO ()
main = do
        doDots [ ("ex1" , graphToDot ex1Params concatene) ]