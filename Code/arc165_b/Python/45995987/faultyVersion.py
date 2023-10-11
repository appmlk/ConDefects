import math
import numpy
N,M = input().split()
N = int(N)
K = int(M)
#print(N+K)
list1 = list(map(int, input().split()))
list2 = []
counter1 = 0
counter2 = 0
counter3 = 0
counter4 = 0
counter5 = 0
#print(list1)
for a in range(N-1):
  if list1[a] <= list1[a+1]:
    counter1 += 1
    counter2 = max(counter1, counter2)
  else:
    counter1 = 0
if counter2+1 >= K:
  for b in range(N):
    print(list1[b], end = " ")
else:
  """for c in range(1, K+1):
    #比較する cの時、N-K-c+2からN-cの最小値とN-K-c+1の値を比較。後者が最小値以下なら1個ずらせる。ずらせなくなるまでやる。
    if c == 1:#特別な処理、最小値比較
      for d in range(N-K-c+1, N-c):
        if list1[N-K-c] > list1[d]:#ずらせない
          counter3 = 1
      if counter3 == 1:#１個ずらせない場合
        counter4 = 0#ずらせる個数カウント
        break
      else:#一個ずらせる
        counter4 += 1
    #全部通過してc=2のループに入るやつは１個ずらせるやつ
    else:#c>=2の処理
      if list1[N-K-c] > list1[N-K-c+1]:#ずれせない場合
        break
      else:
        counter4 += 1
#この時点でcounter4にいくらずらせるかの情報が入っているはず
  #print(counter4)←確認用"""
  
  for c in range(K-1):
    if list1[N-K-c] >= list1[N-K-c-1]:#昇順連続記録を調べる
      counter3  += 1
    else:
      break
  #この時点でcounter3に連続昇順記録-1の値が入ってるはず
  print(counter3)
  for g in range(N-K, N-counter3):
    if list1[N-K-1] > list1[g]:#list1[N-K]が最小値でない場合
      counter5 = 1
  if counter5 == 1:#リストの一番最後の身を入れ替える。
    counter4 = 0
  elif counter5 == 0:
    counter4 = counter3 
  else:
    print("エラー")
    
  
  for d in range(K):
    list2.append(list1[N-K-counter4+d])
  #print(list2)
  sorted_list2 = numpy.sort(list2, axis=-1, kind='quicksort', order=None)
  #print(sorted_list2)
  for e in range(K):
    list1[N-K-counter4+e] = sorted_list2[e]
  for f in range(N):
    print(list1[f], end = " ")
  

      
      
      
      
  
