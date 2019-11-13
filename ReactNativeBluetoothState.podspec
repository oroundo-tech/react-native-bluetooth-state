package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = 'ReactNativeBluetoothState'
  s.version      = package['version']
  s.summary      = package['description']
  s.license      = package['license']

  s.authors      = package['author']
  s.homepage     = package['homepage']
  s.platform     = :ios, "9.0"

  s.source       = { :git => 'https://github.com/oroundo-tech/react-native-bluetooth-state' }
  s.source_files = 'RNBluetoothState.{h,m}'

  s.dependency 'React'
end
