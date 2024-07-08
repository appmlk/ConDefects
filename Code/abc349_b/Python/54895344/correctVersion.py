S=input()

ans="Yes"

word_list=[0]*26

for s in S:
    word_list[ord(s)-ord("a")]+=1

# wordlistの1とか2とかを数えるリストを考える
word_count_list=[0]*101

for i in range(len(word_count_list)):
    word_count_list[i]=word_list.count(i)


for i in range(1,len(word_count_list)):
    if word_count_list[i] == 0 or word_count_list[i]== 2:
        continue
    else:
        ans="No"

print(ans)

