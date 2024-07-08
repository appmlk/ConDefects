import sys
def input():
    input = sys.stdin.read
    data = input().split()
    
    N = int(data[0])
    M = int(data[1])
    
    A = list(map(int, data[2:M+2]))
    X = []
    index = M + 2
    
    for i in range(N):
        row = list(map(int, data[index:index + M]))
        X.append(row)
        index += M
    
    return N, M, A, X
    
def main():
  N, M, A ,X = input()
  
  ans = "Yes"
  
  for i in range(M):
    total = 0
    for j in range (N):
      total += X[j][i]
    if total < A[i]:
      ans = "No"
      print(ans)
      return
  
  print(ans)
  return
      
if __name__ == "__main__":
    main()     
    