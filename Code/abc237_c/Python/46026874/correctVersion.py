S=input()
N=len(S)

if set(S)=={'a'}:
    print('Yes')
    exit()

left=0
right=N-1

while left<N and S[left]=='a':
    left+=1

while 0<=right and S[right]=='a':
    right-=1

if left>N-1-right:
    print('No')
    exit()

S=S[left:right+1]

def palindrome(string):
    length=len(string)
    for i in range(length//2):
        if string[i]!=string[-(i+1)]:
            return False
    return True

print('Yes' if palindrome(S) else 'No')