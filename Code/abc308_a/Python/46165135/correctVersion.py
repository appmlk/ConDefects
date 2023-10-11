S = list(map(int, input().split()))

res = 'Yes'

for i in range(len(S) - 1):
    if S[i] > S[i + 1]:
        res = 'No'
        break
      
for i in range(len(S)):
    if S[i] % 25 or not 100 <= S[i] <= 675:
        res = 'No'
        break
      
print(res)