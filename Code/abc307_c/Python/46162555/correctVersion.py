import copy
h1,w1=map(int,input().split())
A=[list(input()) for i in range(h1)]
h2,w2=map(int,input().split())
B=[list(input()) for i in range(h2)]
h3,w3=map(int,input().split())
X=[list(input()) for i in range(h3)]
def left_up(A,L,h,w):
  h1,h2,w1,w2=L[0],L[1],L[2],L[3]
  B=[["." for i in range(w)] for j in range(h)]
  for i in range(h1,h2):
    for j in range(w1,w2):
      B[i-h1][j-w1]=A[i][j]
  return B
def parametor(A,h,w):
  h1,h2,w1,w2=11,-1,11,-1
  for i in range(h):
    for j in range(w):
      if A[i][j]=="#":
        h1=min(h1,i)
        h2=max(h2,i+1)
        w1=min(w1,j)
        w2=max(w2,j+1)
  l=[h1,h2,w1,w2]
  return l
def new_para(L):
  L[1],L[3]=L[1]-L[0],L[3]-L[2]
  return [L[1],L[3]]
def make(A,h,w):
  L=parametor(A,h,w)
  A=left_up(A,L,h,w)
  L=new_para(L)
  return A,L
def renew(X):
  L=[]
  for i in range(X[1][0]):
    l=[]
    for j in range(X[1][1]):
      l.append(X[0][i][j])
      if j==X[1][1]-1:
        L.append(l)
  return L
A=make(A,h1,w1)
B=make(B,h2,w2)
X=make(X,h3,w3)

new_A=renew(A)
PA=A[1]
new_B=renew(B)
PB=B[1]
new_X=renew(X)
PX=X[1]
ans="No"
if PA[0]>PX[0] or PA[1]>PX[1] or PB[0]>PX[0] or PB[1]>PX[1]:
  pass
else:
  h1,w1,h2,w2,h3,w3=PA[0],PA[1],PB[0],PB[1],PX[0],PX[1]
  for H1 in range(h3-h1+1):
    for W1 in range(w3-w1+1):
      K1=[["." for i in range(w3)] for j in range(h3)]
      for H11 in range(h1):
        for W11 in range(w1):
          if K1[H1+H11][W1+W11]!="#":
            K1[H1+H11][W1+W11]=new_A[H11][W11]
      for H2 in range(h3-h2+1):
        for W2 in range(w3-w2+1):
          K2=copy.deepcopy(K1)
          for H22 in range(h2):
            for W22 in range(w2):
              if K2[H2+H22][W2+W22]!="#":
                K2[H2+H22][W2+W22]=new_B[H22][W22]
          if K2==new_X:
            ans="Yes"
print(ans)