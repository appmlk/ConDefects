l,r = map(int, input().split())

s = input()

print(s[:l] + s[l:r+1][::-1] + s[r+1:])