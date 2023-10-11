S = input()
T = input()

k = ord(S[0]) - ord(T[0])

for i in range(1, len(S)):
    if ((ord(S[i]) - k) - ord('a')) % 26 != ord(T[i]) - ord('a'):
        print('No')
        exit()
print('Yes')
