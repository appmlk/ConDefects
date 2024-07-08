import itertools
import copy
N=int(input())
R=input()
C=input()
def solve5():
  ite=set(itertools.permutations("ABC.."))
  ite=tuple(ite)
  fl=[]
  for i in range(len(ite)):
    for j in range(len(ite[i])):
      if ite[i][j]!=".":
        fl.append(ite[i][j])
        break
  leng=len(ite)
  # ans=0

  def isOkCol(board):
    for i in range(5):
      count=0
      for j in range(5):
        if board[j][i]==".":
          count+=1
      if count!=2:
        return False
    for i in range(5):
      for j in range(5):
        if board[j][i]!=".":
          if board[j][i]!=C[i]: return False
          break
    for i in range(5):
      set_=set()
      for j in range(5):
        set_.add(board[j][i])
      if len(set_)!=4: return False
    return True

  for i in range(leng):
    if fl[i]!=R[0]: continue

    for j in range(leng):
      if fl[j]!=R[1]: continue

      for k in range(leng):
        if fl[k]!=R[2]: continue

        for l in range(leng):
          if fl[l]!=R[3]: continue
            
          for m in range(leng):
            if fl[m]!=R[4]: continue

            board = (ite[i], ite[j], ite[k], ite[l], ite[m])
            if isOkCol(board):
              print("Yes")
              for elm in board:
                print("".join(elm))
              exit()
  print("No")

def solve4():
  ite=set(itertools.permutations("ABC."))
  ite=tuple(ite)
  fl=[]
  for i in range(len(ite)):
    for j in range(len(ite[i])):
      if ite[i][j]!=".":
        fl.append(ite[i][j])
        break
  leng=len(ite)
  # ans=0

  def isOkCol(board):
    for i in range(4):
      count=0
      for j in range(4):
        if board[j][i]==".":
          count+=1
      if count!=1:
        return False
    for i in range(4):
      for j in range(4):
        if board[j][i]!=".":
          if board[j][i]!=C[i]: return False
          break
    for i in range(4):
      set_=set()
      for j in range(4):
        set_.add(board[j][i])
      if len(set_)!=4: return False
    return True

  for i in range(leng):
    if fl[i]!=R[0]: continue

    for j in range(leng):
      if fl[j]!=R[1]: continue

      for k in range(leng):
        if fl[k]!=R[2]: continue

        for l in range(leng):
          if fl[l]!=R[3]: continue

          board = (ite[i], ite[j], ite[k], ite[l])
          if isOkCol(board):
            print("Yes")
            for elm in board:
              print("".join(elm))
            exit()

  print("No")

def solve3():
  def isOk(board):
    if R!=board[0][0]+board[1][0]+board[2][0]: return False
    if C!=board[0][0]+board[0][1]+board[0][2]: return False
    for i in range(3):
      set_=set()
      for j in range(3):
        set_.add(board[j][i])
      if len(set_)!=3:
        return False
    return True
  ite=tuple(itertools.permutations("ABC"))
  for i in range(len(ite)):
    for j in range(len(ite)):
      for k in range(len(ite)):
        board = (ite[i], ite[j], ite[k])
        if isOk(board):
          print("Yes")
          for elm in board:
            print("".join(elm))
          exit()
  print("No")

if N==5: solve5()
if N==4: solve4()
if N==3: solve3()
