condition = list(map(int, input().split()))
word_lengths = list(map(int, input().split()))

# # 単語数
# N = condition[0]
# 行数
M = condition[1]

# Wの最小値（単語の長さの最大値を取得）
lower = max(word_lengths) - 1

# # すべての単語をすべての行に隙間なく埋めた場合の1行の長さ
# min_length = int(sum(word_lengths) / M)

# Wの最大値（すべて1行に並べたときの長さ）
upper = sum(word_lengths) + len(word_lengths) - 1

# for w in range(lower, upper):
while lower + 1 < upper:
  column = 1
  row_len = 0

  # 2分探索
  # middle = int((lower+upper)/2)
  middle = (lower+upper) // 2
  
  # print(w)
  # for i in range(0, N):
  for l in word_lengths:

    len = l
    
    # 超えた時点で次の行にして、該当行の長さを0にする
    # ※ここでのwは範囲の最小値ではなく、範囲の中間地点
    if row_len + len > middle:
      column += 1
      row_len = 0
    row_len += len + 1
    
    # row_len += 1
    # # 該当行が中間地点を超えていない場合空欄補充
    # if middle - row_len > 1: # 横幅と同じ長さであればもちろん不要、横幅と1文字しか空いていない場合もそれ以上入れられないので空欄不要
    #   row_len += 1

  if column > M: # 行数が超えた場合
    lower = middle # 横幅が小さすぎるので、範囲の最小値をmiddleにあげる
  else: # 行数が超えなかった場合
    upper = middle # 横幅が大きすぎるので、範囲の最大値をmiddleに下げる

print(upper)