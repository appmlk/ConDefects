S = input()
T = input()
k = ord(T[0]) - ord(S[0]) if ord(T[0]) - ord(S[0]) >= 0 else ord(T[0]) - ord(S[0])+26
for i in range(len(S)-1):  
    l = ord(T[i+1]) - ord(S[i+1]) if ord(T[i+1]) - ord(S[i+1]) >= 0 else ord(T[i+1]) - ord(S[i+1])+26
    if not (k==l):
        print('No')
        exit()
        
print('Yes')