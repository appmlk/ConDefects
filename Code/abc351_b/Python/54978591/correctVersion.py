def find_differing_cell(N, A, B):
  for i in range(N):
      for j in range(N):
          if A[i][j] != B[i][j]:
            print(i+1, j+1)
            return (i + 1, j + 1)

N=int(input())
A = [['a' for x in range(N)] for y in range(N)] 
B = [['a' for x in range(N)] for y in range(N)] 

for i in range(N):
  ch = str(input())
  for j in range(N):
    A[i][j] = ch[j]
    
for i in range(N):
  ch = str(input())
  for j in range(N):
    B[i][j] = ch[j]

#print(N,A,B)
find_differing_cell(N, A, B)