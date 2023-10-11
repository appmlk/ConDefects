def main():
  H, W = map(int, input().split())
  C = []
  for i in range(H):
    C.append(list(input()))
  for i in range(H):
    for j in range(W):
      if C[i][j] != ".": continue
      d = set(["1","2","3","4","5"])
      if i > 0: d.discard(C[i-1][j])
      if j > 0: d.discard(C[i][j-1])
      if i < H-1: d.discard(C[i+1][j])
      if j < W-1: d.discard(C[i][j+1])
      C[i][j] = d.pop()
  for i in range(H):
    print("".join(C[i]))
  
if __name__ == '__main__':
  main()