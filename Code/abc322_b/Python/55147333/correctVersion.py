length1, length2 = map(int,input().split())
word1 = str(input())
word2 = str(input())
if word2[(length2-length1):length2] == word1:
	a = True
else:
	a = False

if word2[0:length1] == word1:
	b = True
else:
	b = False

if a == True and b == True:
	print(0)
elif a == False and b == True:
	print(1)
elif a == True and b == False:
	print(2)
elif a == False and b == False:
	print(3)
