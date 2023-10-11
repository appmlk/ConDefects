S = input()
T = input()

k = ord(S[0]) - ord(T[0])

for i in range(1, len(S)):
    if chr(ord(S[i]) - k) != T[i]:
        print('No')
        exit()
print('Yes')
