N = int(input())
S = []
for i in range(N):
  s = input()
  S.append(s)
  
flag = False

for i in range(N):
  for j in range(N):
    if i == j:
      break
    
    a = S[i] + S[j]
    
    if a == a[::-1]:
      flag = True
        
print('Yes' if flag else 'No')