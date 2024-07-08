S = input()
T = input()
if T[-1] == 'X':
    T = T[0:2]
T = T.lower()

j = 0
for i in range(len(T)):
    while j < len(S):
        if T[i] == S[j]:
            j += 1
            break
        j += 1
if j == len(S):
    print("No")
else:
    print("Yes")